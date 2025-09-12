package machines;

import register.Registered;

import static machines.Voltage.*;

public class Machines extends Registered<Machine> {
    public Machines() {
        super("Machines");
    }

    public static final Machine CELL_CYCLING = new Machine("system","cycling", Low);
    public static final Machine PLAYER = new Machine("player","manual");

    public static final Machine ADVANCED_EXTRUDER = new Machine("gregtech","advanced_extruder", Medium);
    public static final Machine ADVANCED_FLUID_EXTRACTOR = new Machine("gregtech","advanced_fluid_extractor", Medium);
    public static final Machine BASIC_ALLOY_SMELTER = new Machine("gregtech","basic_alloy_smelter", Low);
    public static final Machine BASIC_ARC_FURNACE = new Machine("gregtech","basic_arc_furnace", Low);
    public static final Machine BASIC_ASSEMBLING_MACHINE = new Machine("gregtech","basic_assembling_machine", Low);
    public static final Machine BASIC_BENDING_MACHINE = new Machine("gregtech","basic_bending_machine", Low);
    public static final Machine BASIC_CENTRIFUGE = new Machine("gregtech","basic_centrifuge", Low);
    public static final Machine BASIC_CIRCUIT_ASSEMBLER = new Machine("gregtech","basic_circuit_assembler", Low);
    public static final Machine BASIC_COMPRESSOR = new Machine("gregtech","basic_compressor", Low);
    public static final Machine BASIC_FLUID_EXTRACTOR = new Machine("gregtech","basic_fluid_extractor", Low);
    public static final Machine BASIC_FORGE_HAMMER = new Machine("gregtech","basic_forge_hammer", Low);
    public static final Machine BASIC_MACERATOR = new Machine("gregtech","basic_macerator", Low);
    public static final Machine BASIC_MIXER = new Machine("gregtech","basic_mixer", Low);
    public static final Machine BASIC_ROCK_BREAKER = new Machine("gregtech","basic_rock_breaker", Low);
    public static final Machine BASIC_SIFTER = new Machine("gregtech","basic_sifter", Low);
    public static final Machine BASIC_WIREMILL = new Machine("gregtech","basic_wiremill", Low);
    public static final Machine CROP_MANAGER_LV = new Machine("gregtech","crop_manager_lv", Low);
    public static final Machine ELECTRIC_BLAST_FURNACE_LV_NICU = new Machine("gregtech","electric_blast_furnace_lv_nicu", Low, 400);
    public static final Machine LOW_VOLTAGE_FLUID_TANK = new Machine("gregtech","low_voltage_fluid_tank", Low);
}