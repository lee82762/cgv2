package com.example.cgv.domain.dto.Movie;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class MovieListDto {
    private String prfpdfrom;
    private String fcltynm;
    private String openrun;
    private String prfpdto;
    private String mt20id;
    private String prfnm;
    private String genrenm;
    private String poster;
    private String prfstate;

/*    @Builder
    public MovieListDto(String prfpdfrom,String fcltynm,String openrun,String prfpdto,String mt20id,String prfnm,String genrenm,String poster,String prfstate) {
        this.prfpdfrom=prfpdfrom;
        this.fcltynm=fcltynm;
        this.openrun=openrun;
        this.prfpdto=prfpdto;
        this.mt20id=mt20id;
        this.prfnm=prfnm;
        this.genrenm=genrenm;
        this.poster=poster;
        this.prfstate=prfstate;
    }*/

}
