package com.music.domain.close;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author linmaojun
 * 类名称: Content
 * 类描述:
 * 创建时间: 2019年07月18日  11:41
 */
@Data
public class Content{
    private Detail detail;

    @lombok.Data
    public static class Detail{
        private SubLine sub;

        @lombok.Data
        public static class SubLine{
            private List<Data> subline;

            @lombok.Data
            public static class Data{

                /**
                 * 收货地址编码【货主】
                 */
                private String address;

                /**
                 * 赛飞关单日期
                 */
                @JSONField(format = "yyyy-MM-dd HH:mm:ss")
                private Date approvedate;

                /**
                 * 出库总单号【物流】
                 */
                private String belongno;

                /**
                 * 业务单号
                 */
                private String billno;

                /**
                 * 制单日期【货主】
                 */
                @JSONField(format = "yyyy-MM-dd HH:mm:ss")
                private Date credate;

                /**
                 * 预留字段1
                 */
                private String def1;

                /**
                 * 预留字段2
                 */
                private String def2;

                /**
                 * 预留字段3
                 */
                private String def3;

                /**
                 * 预留字段4
                 */
                private String def4;

                /**
                 * 物流关单日期
                 */
                @JSONField(format = "yyyy-MM-dd HH:mm:ss")
                private Date expdate;

                /**
                 * 订单件数
                 */
                private String ordpcs;

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
                 * 客商编码【货主】
                 */
                private String owneroid;

                /**
                 * 拣货日期
                 */
                @JSONField(format = "yyyy-MM-dd HH:mm:ss")
                private Date pickingdate;

                /**
                 * 收货地址【客服】
                 */
                private String receiveaddr;

                /**
                 * 客商名称
                 */
                private String receivehead;

                /**
                 * 收货人
                 */
                private String receiveman;

                /**
                 * 产品明细
                 */
                private List<CloseOrderProduct> sublinedetail;
            }
        }
    }
}
