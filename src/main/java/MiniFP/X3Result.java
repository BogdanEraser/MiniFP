package MiniFP;

import java.math.BigDecimal;

/**
 * Created by Eraser on 24.02.2016.
 */
public class X3Result {
    public byte par3;
    public int code;
    public BigDecimal price;
    public String name;
    public long barcode;
    public int qty;
    public BigDecimal qty_in;
    public BigDecimal MRKP_in;
    public BigDecimal RDCT_in;
    public BigDecimal TRNOVR_in;
    public BigDecimal qty_out;
    public BigDecimal MRKP_out;
    public BigDecimal RDCT_out;
    public BigDecimal TRNOVR_out;

    public X3Result() {
    }

    @Override
    public String toString() {
        return "X3Result{" +
                "par3=" + par3 +
                ", code=" + code +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", barcode=" + barcode +
                ", qty=" + qty +
                ", qty_in=" + qty_in +
                ", MRKP_in=" + MRKP_in +
                ", RDCT_in=" + RDCT_in +
                ", TRNOVR_in=" + TRNOVR_in +
                ", qty_out=" + qty_out +
                ", MRKP_out=" + MRKP_out +
                ", RDCT_out=" + RDCT_out +
                ", TRNOVR_out=" + TRNOVR_out +
                '}';
    }

    public byte getPar3() {
        return par3;
    }

    public void setPar3(byte par3) {
        this.par3 = par3;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getQty_in() {
        return qty_in;
    }

    public void setQty_in(BigDecimal qty_in) {
        this.qty_in = qty_in;
    }

    public BigDecimal getMRKP_in() {
        return MRKP_in;
    }

    public void setMRKP_in(BigDecimal MRKP_in) {
        this.MRKP_in = MRKP_in;
    }

    public BigDecimal getRDCT_in() {
        return RDCT_in;
    }

    public void setRDCT_in(BigDecimal RDCT_in) {
        this.RDCT_in = RDCT_in;
    }

    public BigDecimal getTRNOVR_in() {
        return TRNOVR_in;
    }

    public void setTRNOVR_in(BigDecimal TRNOVR_in) {
        this.TRNOVR_in = TRNOVR_in;
    }

    public BigDecimal getQty_out() {
        return qty_out;
    }

    public void setQty_out(BigDecimal qty_out) {
        this.qty_out = qty_out;
    }

    public BigDecimal getMRKP_out() {
        return MRKP_out;
    }

    public void setMRKP_out(BigDecimal MRKP_out) {
        this.MRKP_out = MRKP_out;
    }

    public BigDecimal getRDCT_out() {
        return RDCT_out;
    }

    public void setRDCT_out(BigDecimal RDCT_out) {
        this.RDCT_out = RDCT_out;
    }

    public BigDecimal getTRNOVR_out() {
        return TRNOVR_out;
    }

    public void setTRNOVR_out(BigDecimal TRNOVR_out) {
        this.TRNOVR_out = TRNOVR_out;
    }
}
