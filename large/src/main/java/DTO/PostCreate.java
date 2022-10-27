package DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostCreate {
    private String title;
    private String body;

    private Boolean ifads;

    @Builder
    public PostCreate(String title, String body, Boolean ifads) {
        this.title = title;
        this.body = body;
        this.ifads = ifads;
    }
}
