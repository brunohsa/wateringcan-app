package wateringcan.com.br.wateringcan.model;

import java.io.Serializable;

/**
 * Created by Bruno on 04/11/2017.
 */

public class Configuration implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Integer value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
