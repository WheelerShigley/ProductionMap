package machines;

public enum Voltage {
    None,
    UltraLow,
    Low,
    Medium,
    High,
    Extreme,
    Insane,
    Ludicrous,
    ZPM,
    Ultimate,
    HighlyUltimate,
    ExtremelyUltimate,
    InsanelyUltimate,
    MegaUltimate,
    ExtendedMegaUltimate,
    Maximum
    ;

    @Override
    public String toString() {
        return switch(this) {
            case UltraLow -> "ULV";
            case Low -> "LV";
            case Medium -> "MV";
            case High -> "HV";
            case Extreme -> "EV";
            case Insane -> "IV";
            case Ludicrous -> "LuV";
            case ZPM -> "ZPM";
            case Ultimate -> "UV";
            case HighlyUltimate -> "UHV";
            case ExtremelyUltimate -> "UEV";
            case InsanelyUltimate -> "UIV";
            case MegaUltimate -> "UMV";
            case ExtendedMegaUltimate -> "UXV";
            case Maximum -> "MAX";
            default -> "";
        };
    }

    public int EULimit() {
        return switch(this) {
            case UltraLow ->                1<<3;
            case Low ->                     1<<5;
            case Medium ->                  1<<7;
            case High ->                    1<<9;
            case Extreme ->                 1<<11;
            case Insane ->                  1<<13;
            case Ludicrous ->               1<<15;
            case ZPM ->                     1<<17;
            case Ultimate ->                1<<19;
            case HighlyUltimate ->          1<<21;
            case ExtremelyUltimate ->       1<<23;
            case InsanelyUltimate ->        1<<25;
            case MegaUltimate ->            1<<27;
            case ExtendedMegaUltimate ->    1<<29;
            case Maximum ->                 Integer.MAX_VALUE;
            default ->                      0;
        };
    }
}
