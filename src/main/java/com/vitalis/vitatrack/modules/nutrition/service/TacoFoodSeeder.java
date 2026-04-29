package com.vitalis.vitatrack.modules.nutrition.service;

import com.vitalis.vitatrack.modules.nutrition.domain.Food;
import com.vitalis.vitatrack.modules.nutrition.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TacoFoodSeeder implements CommandLineRunner {

    private final FoodRepository foodRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> TacoFoodSeeder rodando...");
        System.out.println(">>> Total foods antes: " + foodRepository.count());

        if (foodRepository.count() > 0) {
            System.out.println(">>> Importação cancelada: já existem alimentos cadastrados.");
            return;
        }

        ClassPathResource resource = new ClassPathResource("data/taco_foods.xlsx");

        System.out.println(">>> Arquivo existe? " + resource.exists());

        if (!resource.exists()) {
            System.out.println(">>> ERRO: arquivo taco_foods.xlsx não encontrado em src/main/resources/data/");
            return;
        }

        try (Workbook workbook = WorkbookFactory.create(resource.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            int imported = 0;

            for (Row row : sheet) {
                if (row.getRowNum() < 4) {
                    continue;
                }

                String nome = getText(row.getCell(1));

                if (nome == null || nome.isBlank()) {
                    continue;
                }

                if (isLinhaDeCategoriaOuCabecalho(nome)) {
                    continue;
                }

                Double calorias = getNumber(row.getCell(3));      // D - Energia kcal
                Double proteinas = getNumber(row.getCell(5));     // F - Proteína
                Double gorduras = getNumber(row.getCell(6));      // G - Lipídeos
                Double carboidratos = getNumber(row.getCell(8));  // I - Carboidrato

                Food food = Food.builder()
                        .nome(nome)
                        .calorias(round(calorias))
                        .proteinas(round(proteinas))
                        .carboidratos(round(carboidratos))
                        .gorduras(round(gorduras))
                        .build();

                foodRepository.save(food);
                imported++;
            }

            System.out.println(">>> Alimentos importados: " + imported);
        }

        System.out.println(">>> Total foods depois: " + foodRepository.count());
    }

    private boolean isLinhaDeCategoriaOuCabecalho(String nome) {
        return nome.equalsIgnoreCase("Descrição dos alimentos")
                || nome.equalsIgnoreCase("Cereais e derivados")
                || nome.equalsIgnoreCase("Verduras, hortaliças e derivados")
                || nome.equalsIgnoreCase("Frutas e derivados")
                || nome.equalsIgnoreCase("Gorduras e óleos")
                || nome.equalsIgnoreCase("Pescados e frutos do mar")
                || nome.equalsIgnoreCase("Carnes e derivados")
                || nome.equalsIgnoreCase("Leite e derivados")
                || nome.equalsIgnoreCase("Bebidas")
                || nome.equalsIgnoreCase("Ovos e derivados")
                || nome.equalsIgnoreCase("Produtos açucarados")
                || nome.equalsIgnoreCase("Miscelâneas")
                || nome.equalsIgnoreCase("Outros alimentos industrializados")
                || nome.equalsIgnoreCase("Alimentos preparados")
                || nome.equalsIgnoreCase("Leguminosas e derivados")
                || nome.equalsIgnoreCase("Nozes e sementes");
    }

    private String getText(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue()).trim();
        }

        if (cell.getCellType() == CellType.FORMULA) {
            try {
                return cell.getStringCellValue().trim();
            } catch (Exception e) {
                return String.valueOf(cell.getNumericCellValue()).trim();
            }
        }

        return null;
    }

    private Double getNumber(Cell cell) {
        if (cell == null) {
            return 0.0;
        }

        try {
            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            }

            if (cell.getCellType() == CellType.STRING) {
                String value = cell.getStringCellValue()
                        .trim()
                        .replace(",", ".");

                if (value.isBlank()
                        || value.equalsIgnoreCase("NA")
                        || value.equalsIgnoreCase("Tr")
                        || value.equals("-")) {
                    return 0.0;
                }

                return Double.parseDouble(value);
            }

            if (cell.getCellType() == CellType.FORMULA) {
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    return 0.0;
                }
            }

            return 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    private Double round(Double value) {
        if (value == null) {
            return 0.0;
        }

        return Math.round(value * 100.0) / 100.0;
    }
}