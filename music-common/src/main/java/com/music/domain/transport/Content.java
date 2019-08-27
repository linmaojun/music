package com.music.domain.transport;

import com.music.domain.close.CloseOrderProduct;
import lombok.Data;

import java.util.List;

/**
 * @author linmaojun
 * 类名称: Content
 * 类描述:
 * 创建时间: 2019年07月18日  11:41
 */
@Data
public class Content {
    private Detail detail;

    @Data
    public static class Detail{
        private SubLine sub;

        @Data
        public static class SubLine{
            private List<Data> subline;

            @lombok.Data
            public static class Data{
                /**
                 * 出库总单号【物流】
                 */
                private String belongno;

                /**
                 * 业务单号
                 */
                private String billno;

                /**
                 * 承运单位
                 */
                private String carrierno;

                /**
                 * 制单日期【货主】
                 */
                private String credate;

                /**
                 *
                 */
                private String def1;

                /**
                 *
                 */
                private String def2;

                /**
                 *
                 */
                private String def3;

                /**
                 *
                 */
                private String def4;

                /**
                 * 配送员
                 */
                private String deliverystaffs;

                /**
                 * 司机
                 */
                private String drivers;

                /**
                 * 派车单号
                 */
                private String ecno;

                /**
                 *发运日期
                 */
                private String leavetime;

                /**
                 * 签收人
                 */
                private String signperson;

                /**
                 * 签收日期
                 */
                private String signtime;

                /**
                 * 运单号
                 */
                private String legno;

                /**
                 * 运单件数
                 */
                private String legpcs;

                /**
                 * 配载方式
                 */
                private String loadingmethod;

                /**
                 * 出库总单号【货主】
                 */
                private String orderoid;

                /**
                 * 货主编码
                 */
                private String owner;

                /**
                 * 货主编码
                 */
                private String ownerid;

                /**
                 * 货主名称
                 */
                private String ownername;

                /**
                 * 运输方式
                 */
                private String transmode;

                /**
                 * 车牌号1
                 */
                private String vehicelno1;

                /**
                 * 车牌号2
                 */
                private String vehicelno2;

                /**
                 * 车牌号3
                 */
                private String vehicelno3;

                /**
                 * 车牌号4
                 */
                private String vehicelno4;

                /**
                 * 车牌号5
                 */
                private String vehicelno5;

                /**
                 * 配送标记
                 */
                private Integer waybilltype;

                /**
                 * 产品明细
                 */
                private List<TransportProduct> sublinedetail;
            }
        }
    }
}
