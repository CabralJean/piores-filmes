package com.texo.pioresfilmes.domain;

import java.util.List;

public class Resultado {

    private List<Premiacao> max;
    private List<Premiacao> min;


    public Resultado(List<Premiacao> max, List<Premiacao> min) {
        this.max = max;
        this.min = min;
    }

    public List<Premiacao> getMax() {
        return max;
    }

    public void setMax(List<Premiacao> max) {
        this.max = max;
    }

    public List<Premiacao> getMin() {
        return min;
    }

    public void setMin(List<Premiacao> min) {
        this.min = min;
    }
}
