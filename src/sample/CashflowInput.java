package sample;

public class CashflowInput {
    private int YEARS = 100;
    private double indexation = 0.025;
    private String name;
    private double amount = 1;
    private int startYear = 1;
    private int endYear = 20;
    private int frequency = 1;
    private double year1;
    private double year2;
    private double year3;
    private double year4;
    private double year5;
    private double year6;
    private double year7;
    private double year8;
    private double year9;
    private double year10;
    private double year11;
    private double year12;
    private double year13;
    private double year14;
    private double year15;
    private double year16;
    private double year17;
    private double year18;
    private double year19;
    private double year20;
    private double year21;
    private double year22;
    private double year23;
    private double year24;
    private double year25;
    private double year26;
    private double year27;
    private double year28;
    private double year29;
    private double year30;
    private double year31;
    private double year32;
    private double year33;
    private double year34;
    private double year35;
    private double year36;
    private double year37;
    private double year38;
    private double year39;
    private double year40;
    private double year41;
    private double year42;
    private double year43;
    private double year44;
    private double year45;
    private double year46;
    private double year47;
    private double year48;
    private double year49;
    private double year50;
    private double year51;
    private double year52;
    private double year53;
    private double year54;
    private double year55;
    private double year56;
    private double year57;
    private double year58;
    private double year59;
    private double year60;
    private double year61;
    private double year62;
    private double year63;
    private double year64;
    private double year65;
    private double year66;
    private double year67;
    private double year68;
    private double year69;
    private double year70;
    private double year71;
    private double year72;
    private double year73;
    private double year74;
    private double year75;
    private double year76;
    private double year77;
    private double year78;
    private double year79;
    private double year80;
    private double year81;
    private double year82;
    private double year83;
    private double year84;
    private double year85;
    private double year86;
    private double year87;
    private double year88;
    private double year89;
    private double year90;
    private double year91;
    private double year92;
    private double year93;
    private double year94;
    private double year95;
    private double year96;
    private double year97;
    private double year98;
    private double year99;
    private double year100;

    public CashflowInput(String name, double amount, int startYear, int endYear, int frequency, double indexation) {
        this.name = name;
        this.amount = amount;
        if(startYear < 1){
            this.startYear = 1;
        } else {
            this.startYear = startYear;
        }
        this.endYear = endYear;
        this.frequency = frequency;

        double[] data = new double[YEARS];
        for (int i = this.startYear - 1; i < this.endYear; i += frequency) {
            data[i] = Math.round(amount * Math.pow((1 + indexation), i) * 100d) / 100d;
        }
        setYearsData(data);
    }

    public CashflowInput(String name, double indexation) {
        this.name = name;
        double[] data = new double[YEARS];
        for (int i = this.startYear - 1; i < this.endYear; i += frequency) {
            data[i] = Math.round(amount * Math.pow((1 + indexation), i) * 100d) / 100d;
        }
        setYearsData(data);
    }

    public void updateData(){
        double[] data = new double[YEARS];
        for (int i = this.startYear - 1; i < this.endYear; i += frequency) {
            data[i] = Math.round(this.amount * Math.pow((1+InputsController.getIndexation() ), i) * 100d) / 100d;
        }
        setYearsData(data);
    }

    public void setYearsData(double[] values) {
        this.year1 = values[0];
        this.year2 = values[1];
        this.year3 = values[2];
        this.year4 = values[3];
        this.year5 = values[4];
        this.year6 = values[5];
        this.year7 = values[6];
        this.year8 = values[7];
        this.year9 = values[8];
        this.year10 = values[9];
        this.year11 = values[10];
        this.year12 = values[11];
        this.year13 = values[12];
        this.year14 = values[13];
        this.year15 = values[14];
        this.year16 = values[15];
        this.year17 = values[16];
        this.year18 = values[17];
        this.year19 = values[18];
        this.year20 = values[19];
        this.year21 = values[20];
        this.year22 = values[21];
        this.year23 = values[22];
        this.year24 = values[23];
        this.year25 = values[24];
        this.year26 = values[25];
        this.year27 = values[26];
        this.year28 = values[27];
        this.year29 = values[28];
        this.year30 = values[29];
        this.year31 = values[30];
        this.year32 = values[31];
        this.year33 = values[32];
        this.year34 = values[33];
        this.year35 = values[34];
        this.year36 = values[35];
        this.year37 = values[36];
        this.year38 = values[37];
        this.year39 = values[38];
        this.year40 = values[39];
        this.year41 = values[40];
        this.year42 = values[41];
        this.year43 = values[42];
        this.year44 = values[43];
        this.year45 = values[44];
        this.year46 = values[45];
        this.year47 = values[46];
        this.year48 = values[47];
        this.year49 = values[48];
        this.year50 = values[49];
        this.year51 = values[50];
        this.year52 = values[51];
        this.year53 = values[52];
        this.year54 = values[53];
        this.year55 = values[54];
        this.year56 = values[55];
        this.year57 = values[56];
        this.year58 = values[57];
        this.year59 = values[58];
        this.year60 = values[59];
        this.year61 = values[60];
        this.year62 = values[61];
        this.year63 = values[62];
        this.year64 = values[63];
        this.year65 = values[64];
        this.year66 = values[65];
        this.year67 = values[66];
        this.year68 = values[67];
        this.year69 = values[68];
        this.year70 = values[69];
        this.year71 = values[70];
        this.year72 = values[71];
        this.year73 = values[72];
        this.year74 = values[73];
        this.year75 = values[74];
        this.year76 = values[75];
        this.year77 = values[76];
        this.year78 = values[77];
        this.year79 = values[78];
        this.year80 = values[79];
        this.year81 = values[80];
        this.year82 = values[81];
        this.year83 = values[82];
        this.year84 = values[83];
        this.year85 = values[84];
        this.year86 = values[85];
        this.year87 = values[86];
        this.year88 = values[87];
        this.year89 = values[88];
        this.year90 = values[89];
        this.year91 = values[90];
        this.year92 = values[91];
        this.year93 = values[92];
        this.year94 = values[93];
        this.year95 = values[94];
        this.year96 = values[95];
        this.year97 = values[96];
        this.year98 = values[97];
        this.year99 = values[98];
        this.year100 = values[99];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getYear1() {
        return year1;
    }

    public void setYear1(double year1) {
        this.year1 = year1;
    }

    public double getYear2() {
        return year2;
    }

    public void setYear2(double year2) {
        this.year2 = year2;
    }

    public double getYear3() {
        return year3;
    }

    public void setYear3(double year3) {
        this.year3 = year3;
    }

    public double getYear4() {
        return year4;
    }

    public void setYear4(double year4) {
        this.year4 = year4;
    }

    public double getYear5() {
        return year5;
    }

    public void setYear5(double year5) {
        this.year5 = year5;
    }

    public double getYear6() {
        return year6;
    }

    public void setYear6(double year6) {
        this.year6 = year6;
    }

    public double getYear7() {
        return year7;
    }

    public void setYear7(double year7) {
        this.year7 = year7;
    }

    public double getYear8() {
        return year8;
    }

    public void setYear8(double year8) {
        this.year8 = year8;
    }

    public double getYear9() {
        return year9;
    }

    public void setYear9(double year9) {
        this.year9 = year9;
    }

    public double getYear10() {
        return year10;
    }

    public void setYear10(double year10) {
        this.year10 = year10;
    }

    public double getYear11() {
        return year11;
    }

    public void setYear11(double year11) {
        this.year11 = year11;
    }

    public double getYear12() {
        return year12;
    }

    public void setYear12(double year12) {
        this.year12 = year12;
    }

    public double getYear13() {
        return year13;
    }

    public void setYear13(double year13) {
        this.year13 = year13;
    }

    public double getYear14() {
        return year14;
    }

    public void setYear14(double year14) {
        this.year14 = year14;
    }

    public double getYear15() {
        return year15;
    }

    public void setYear15(double year15) {
        this.year15 = year15;
    }

    public double getYear16() {
        return year16;
    }

    public void setYear16(double year16) {
        this.year16 = year16;
    }

    public double getYear17() {
        return year17;
    }

    public void setYear17(double year17) {
        this.year17 = year17;
    }

    public double getYear18() {
        return year18;
    }

    public void setYear18(double year18) {
        this.year18 = year18;
    }

    public double getYear19() {
        return year19;
    }

    public void setYear19(double year19) {
        this.year19 = year19;
    }

    public double getYear20() {
        return year20;
    }

    public void setYear20(double year20) {
        this.year20 = year20;
    }

    public double getYear21() {
        return year21;
    }

    public void setYear21(double year21) {
        this.year21 = year21;
    }

    public double getYear22() {
        return year22;
    }

    public void setYear22(double year22) {
        this.year22 = year22;
    }

    public double getYear23() {
        return year23;
    }

    public void setYear23(double year23) {
        this.year23 = year23;
    }

    public double getYear24() {
        return year24;
    }

    public void setYear24(double year24) {
        this.year24 = year24;
    }

    public double getYear25() {
        return year25;
    }

    public void setYear25(double year25) {
        this.year25 = year25;
    }

    public double getYear26() {
        return year26;
    }

    public void setYear26(double year26) {
        this.year26 = year26;
    }

    public double getYear27() {
        return year27;
    }

    public void setYear27(double year27) {
        this.year27 = year27;
    }

    public double getYear28() {
        return year28;
    }

    public void setYear28(double year28) {
        this.year28 = year28;
    }

    public double getYear29() {
        return year29;
    }

    public void setYear29(double year29) {
        this.year29 = year29;
    }

    public double getYear30() {
        return year30;
    }

    public void setYear30(double year30) {
        this.year30 = year30;
    }

    public double getYear31() {
        return year31;
    }

    public void setYear31(double year31) {
        this.year31 = year31;
    }

    public double getYear32() {
        return year32;
    }

    public void setYear32(double year32) {
        this.year32 = year32;
    }

    public double getYear33() {
        return year33;
    }

    public void setYear33(double year33) {
        this.year33 = year33;
    }

    public double getYear34() {
        return year34;
    }

    public void setYear34(double year34) {
        this.year34 = year34;
    }

    public double getYear35() {
        return year35;
    }

    public void setYear35(double year35) {
        this.year35 = year35;
    }

    public double getYear36() {
        return year36;
    }

    public void setYear36(double year36) {
        this.year36 = year36;
    }

    public double getYear37() {
        return year37;
    }

    public void setYear37(double year37) {
        this.year37 = year37;
    }

    public double getYear38() {
        return year38;
    }

    public void setYear38(double year38) {
        this.year38 = year38;
    }

    public double getYear39() {
        return year39;
    }

    public void setYear39(double year39) {
        this.year39 = year39;
    }

    public double getYear40() {
        return year40;
    }

    public void setYear40(double year40) {
        this.year40 = year40;
    }

    public double getYear41() {
        return year41;
    }

    public void setYear41(double year41) {
        this.year41 = year41;
    }

    public double getYear42() {
        return year42;
    }

    public void setYear42(double year42) {
        this.year42 = year42;
    }

    public double getYear43() {
        return year43;
    }

    public void setYear43(double year43) {
        this.year43 = year43;
    }

    public double getYear44() {
        return year44;
    }

    public void setYear44(double year44) {
        this.year44 = year44;
    }

    public double getYear45() {
        return year45;
    }

    public void setYear45(double year45) {
        this.year45 = year45;
    }

    public double getYear46() {
        return year46;
    }

    public void setYear46(double year46) {
        this.year46 = year46;
    }

    public double getYear47() {
        return year47;
    }

    public void setYear47(double year47) {
        this.year47 = year47;
    }

    public double getYear48() {
        return year48;
    }

    public void setYear48(double year48) {
        this.year48 = year48;
    }

    public double getYear49() {
        return year49;
    }

    public void setYear49(double year49) {
        this.year49 = year49;
    }

    public double getYear50() {
        return year50;
    }

    public void setYear50(double year50) {
        this.year50 = year50;
    }

    public double getYear51() {
        return year51;
    }

    public void setYear51(double year51) {
        this.year51 = year51;
    }

    public double getYear52() {
        return year52;
    }

    public void setYear52(double year52) {
        this.year52 = year52;
    }

    public double getYear53() {
        return year53;
    }

    public void setYear53(double year53) {
        this.year53 = year53;
    }

    public double getYear54() {
        return year54;
    }

    public void setYear54(double year54) {
        this.year54 = year54;
    }

    public double getYear55() {
        return year55;
    }

    public void setYear55(double year55) {
        this.year55 = year55;
    }

    public double getYear56() {
        return year56;
    }

    public void setYear56(double year56) {
        this.year56 = year56;
    }

    public double getYear57() {
        return year57;
    }

    public void setYear57(double year57) {
        this.year57 = year57;
    }

    public double getYear58() {
        return year58;
    }

    public void setYear58(double year58) {
        this.year58 = year58;
    }

    public double getYear59() {
        return year59;
    }

    public void setYear59(double year59) {
        this.year59 = year59;
    }

    public double getYear60() {
        return year60;
    }

    public void setYear60(double year60) {
        this.year60 = year60;
    }

    public double getYear61() {
        return year61;
    }

    public void setYear61(double year61) {
        this.year61 = year61;
    }

    public double getYear62() {
        return year62;
    }

    public void setYear62(double year62) {
        this.year62 = year62;
    }

    public double getYear63() {
        return year63;
    }

    public void setYear63(double year63) {
        this.year63 = year63;
    }

    public double getYear64() {
        return year64;
    }

    public void setYear64(double year64) {
        this.year64 = year64;
    }

    public double getYear65() {
        return year65;
    }

    public void setYear65(double year65) {
        this.year65 = year65;
    }

    public double getYear66() {
        return year66;
    }

    public void setYear66(double year66) {
        this.year66 = year66;
    }

    public double getYear67() {
        return year67;
    }

    public void setYear67(double year67) {
        this.year67 = year67;
    }

    public double getYear68() {
        return year68;
    }

    public void setYear68(double year68) {
        this.year68 = year68;
    }

    public double getYear69() {
        return year69;
    }

    public void setYear69(double year69) {
        this.year69 = year69;
    }

    public double getYear70() {
        return year70;
    }

    public void setYear70(double year70) {
        this.year70 = year70;
    }

    public double getYear71() {
        return year71;
    }

    public void setYear71(double year71) {
        this.year71 = year71;
    }

    public double getYear72() {
        return year72;
    }

    public void setYear72(double year72) {
        this.year72 = year72;
    }

    public double getYear73() {
        return year73;
    }

    public void setYear73(double year73) {
        this.year73 = year73;
    }

    public double getYear74() {
        return year74;
    }

    public void setYear74(double year74) {
        this.year74 = year74;
    }

    public double getYear75() {
        return year75;
    }

    public void setYear75(double year75) {
        this.year75 = year75;
    }

    public double getYear76() {
        return year76;
    }

    public void setYear76(double year76) {
        this.year76 = year76;
    }

    public double getYear77() {
        return year77;
    }

    public void setYear77(double year77) {
        this.year77 = year77;
    }

    public double getYear78() {
        return year78;
    }

    public void setYear78(double year78) {
        this.year78 = year78;
    }

    public double getYear79() {
        return year79;
    }

    public void setYear79(double year79) {
        this.year79 = year79;
    }

    public double getYear80() {
        return year80;
    }

    public void setYear80(double year80) {
        this.year80 = year80;
    }

    public double getYear81() {
        return year81;
    }

    public void setYear81(double year81) {
        this.year81 = year81;
    }

    public double getYear82() {
        return year82;
    }

    public void setYear82(double year82) {
        this.year82 = year82;
    }

    public double getYear83() {
        return year83;
    }

    public void setYear83(double year83) {
        this.year83 = year83;
    }

    public double getYear84() {
        return year84;
    }

    public void setYear84(double year84) {
        this.year84 = year84;
    }

    public double getYear85() {
        return year85;
    }

    public void setYear85(double year85) {
        this.year85 = year85;
    }

    public double getYear86() {
        return year86;
    }

    public void setYear86(double year86) {
        this.year86 = year86;
    }

    public double getYear87() {
        return year87;
    }

    public void setYear87(double year87) {
        this.year87 = year87;
    }

    public double getYear88() {
        return year88;
    }

    public void setYear88(double year88) {
        this.year88 = year88;
    }

    public double getYear89() {
        return year89;
    }

    public void setYear89(double year89) {
        this.year89 = year89;
    }

    public double getYear90() {
        return year90;
    }

    public void setYear90(double year90) {
        this.year90 = year90;
    }

    public double getYear91() {
        return year91;
    }

    public void setYear91(double year91) {
        this.year91 = year91;
    }

    public double getYear92() {
        return year92;
    }

    public void setYear92(double year92) {
        this.year92 = year92;
    }

    public double getYear93() {
        return year93;
    }

    public void setYear93(double year93) {
        this.year93 = year93;
    }

    public double getYear94() {
        return year94;
    }

    public void setYear94(double year94) {
        this.year94 = year94;
    }

    public double getYear95() {
        return year95;
    }

    public void setYear95(double year95) {
        this.year95 = year95;
    }

    public double getYear96() {
        return year96;
    }

    public void setYear96(double year96) {
        this.year96 = year96;
    }

    public double getYear97() {
        return year97;
    }

    public void setYear97(double year97) {
        this.year97 = year97;
    }

    public double getYear98() {
        return year98;
    }

    public void setYear98(double year98) {
        this.year98 = year98;
    }

    public double getYear99() {
        return year99;
    }

    public void setYear99(double year99) {
        this.year99 = year99;
    }

    public double getYear100() {
        return year100;
    }

    public void setYear100(double year0) {
        this.year100 = year100;
    }



}