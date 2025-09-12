package machines;

import register.Registered;

public class Machines extends Registered<Machine> {
    public Machines() {
        super("Machines");
    }

    public static final Machine CELL_CYCLING = new Machine("system","cycling");
    public static final Machine PLAYER = new Machine("player","manual");

    public static final Machine ADVANCED_EXTRUDER = new Machine("gregtech","advanced_extruder");
    public static final Machine ADVANCED_FLUID_EXTRACTOR = new Machine("gregtech","advanced_fluid_extractor");
    public static final Machine BASIC_ALLOY_SMELTER = new Machine("gregtech","basic_alloy_smelter");
    public static final Machine BASIC_ARC_FURNACE = new Machine("gregtech","basic_arc_furnace");
    public static final Machine BASIC_ASSEMBLING_MACHINE = new Machine("gregtech","basic_assembling_machine");
    public static final Machine BASIC_BENDING_MACHINE = new Machine("gregtech","basic_bending_machine");
    public static final Machine BASIC_CENTRIFUGE = new Machine("gregtech","basic_centrifuge");
    public static final Machine BASIC_CIRCUIT_ASSEMBLER = new Machine("gregtech","basic_circuit_assembler");
    public static final Machine BASIC_COMPRESSOR = new Machine("gregtech","basic_compressor");
    public static final Machine BASIC_FLUID_EXTRACTOR = new Machine("gregtech","basic_fluid_extractor");
    public static final Machine BASIC_FORGE_HAMMER = new Machine("gregtech","basic_forge_hammer");
    public static final Machine BASIC_MACERATOR = new Machine("gregtech","basic_macerator");
    public static final Machine BASIC_MIXER = new Machine("gregtech","basic_mixer");
    public static final Machine BASIC_ROCK_BREAKER = new Machine("gregtech","basic_rock_breaker");
    public static final Machine BASIC_SIFTER = new Machine("gregtech","basic_sifter");
    public static final Machine BASIC_WIREMILL = new Machine("gregtech","basic_wiremill");
    public static final Machine CROP_MANAGER_LV = new Machine("gregtech","crop_manager_lv");
    public static final Machine ELECTRIC_BLAST_FURNACE_MV_NICU = new Machine("gregtech","electric_blast_furnace_mv_nicu");
    public static final Machine LOW_VOLTAGE_FLUID_TANK = new Machine("gregtech","low_voltage_fluid_tank");
}