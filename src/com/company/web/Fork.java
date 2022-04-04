package com.company.web;

import java.util.Objects;

public class Fork implements Comparable<Fork> {
    public Fork(Integer num) {
        this.num = num;
    }
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fork)) return false;
        Fork fork = (Fork) o;
        return Objects.equals(num, fork.num);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fork{");
        sb.append("num=").append(num);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public int compareTo(Fork o) {
        return this.num-o.num;
    }
}
