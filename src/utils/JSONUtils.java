package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Lê um arquivo JSON e converte-o para o tipo especificado.
     *
     * @param filePath Caminho do arquivo JSON.
     * @param typeRef  Referência do tipo esperado.
     * @param <T>      Tipo genérico.
     * @return Objeto correspondente ao JSON lido.
     * @throws IOException Caso ocorra erro na leitura do arquivo.
     */
    public static <T> T readFromJSON(String filePath, TypeReference<T> typeRef) throws IOException {
        return objectMapper.readValue(new File(filePath), typeRef);
    }

    /**
     * Grava um objeto em um arquivo JSON.
     *
     * @param filePath Caminho do arquivo JSON.
     * @param data     Objeto a ser gravado.
     * @throws IOException Caso ocorra erro na escrita do arquivo.
     */
    public static void writeToJSON(String filePath, Object data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}
