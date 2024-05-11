package project4.travel.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tourism {

    @Id
    private String PLACE_NM;
    private String GUGUN_NM;
    private String TRRSRT_ROAD_NM_ADDR;
    private String TEL_NO;
    private String GRPNM;
    private String SEASONNM;
    private String IEMNM;
    private String CL_NM;
    private String ETC_CN;

}
