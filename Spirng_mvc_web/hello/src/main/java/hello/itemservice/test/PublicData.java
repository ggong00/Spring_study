package hello.itemservice.test;

import lombok.Data;

import java.util.List;

@Data
public class PublicData {

    public Response response;

    @Data
    public static class Response {
        public Header header;
        public Body body;

        @Data
        public static class Header {
            public int resultCode;
            public String resultMsg;
        }

        @Data
        public static class Body {
            public Items items;
            public int numOfRows;
            public int pageNo;
            public int totalCount;

            @Data
            public static class Items {
                public List<Item> item;

                @Data
                public static class Item {
                    public String addrAmdNm;
                    public String addrCpNm;
                    public String addrCpbNm;
                    public String addrEmdNm;
                    public String atnmChkYn;
                    public String baseYmd;
                    public String cpNm;
                    public String cpYmd;
                    public String cpbNm;
                    public String faciAddr1;
                    public String faciAddr2;
                    public String faciGbNm;
                    public String faciHomepage;
                    public String faciMngType;
                    public String faciNm;
                    public String faciPointX;
                    public String faciPointY;
                    public String faciPost;
                    public String faciRegYmd;
                    public String faciRoadAddr1;
                    public String faciRoadAddr2;
                    public String faciRoadPost;
                    public String faciStat;
                    public String faciTel;
                    public String fcobNm;
                    public String fmngCpNm;
                    public String fmngCpbNm;
                    public String fmngDeptNm;
                    public String fmngTypeGbNm;
                    public String fmngUserTel;
                    public String ftypeNm;
                    public String inoutGbn;
                    public String lifeGymNm;
                    public String nationYn;
                    public String openYn;
                    public String regDt;
                    public String sdwnYmd;
                    public String ssmDsnYn;
                    public String standCptPsnCnt;
                    public String standSeatCnt;
                    public String thYmd;
                    public String totFaciArea;
                    public String updDt;
                    public String useAsctNm;
                }
            }
        }
    }
}
