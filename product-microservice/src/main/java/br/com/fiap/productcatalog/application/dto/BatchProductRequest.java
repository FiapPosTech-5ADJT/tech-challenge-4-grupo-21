package br.com.fiap.productcatalog.application.dto;

import java.util.Arrays;

public record BatchProductRequest(byte[] binary, long miliseconds) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchProductRequest that = (BatchProductRequest) o;
        return miliseconds == that.miliseconds && Arrays.equals(binary, that.binary);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(miliseconds);
        result = 31 * result + Arrays.hashCode(binary);
        return result;
    }

    @Override
    public String toString() {
        return "BatchProductRequest{" +
                "binary=" + Arrays.toString(binary) +
                ", miliseconds=" + miliseconds +
                '}';
    }
}
