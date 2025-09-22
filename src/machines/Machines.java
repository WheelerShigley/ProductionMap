package machines;

import register.Namespaces;
import register.Registered;

import java.util.ArrayList;
import java.util.List;

import static machines.Voltage.*;
import static register.Namespaces.*;

public class Machines extends Registered<Machine> {
    private static Machines instance;
    public static final List<Machine> registry = new ArrayList<Machine>();
    public Machines() {
        super("Machines", registry);
        instance = this;
    }

    public static boolean register(Machine machine) {
        if(instance == null) {
            instance = new Machines();
        }
        return instance.registerInstance(machine);
    }

    public static final Machine CELL_CYCLING = new Machine(WORLD,"cycling", Low);
    public static final Machine CONSOLIDATED_BRANCH = new Machine(Namespaces.PLAYER,"consolidated_branch", None);
    public static final Machine PLAYER = new Machine(Namespaces.PLAYER,"manual");

    public static final Machine ADVANCED_CENTRIFUGE = new Machine(GREGTECH,"advanced_centrifuge", Medium);
    public static final Machine ADVANCED_ELECTROLYZER = new Machine(GREGTECH,"advanced_electrolyzer", Medium);
    public static final Machine ADVANCED_EXTRUDER = new Machine(GREGTECH,"advanced_extruder", Medium);
    public static final Machine ADVANCED_FLUID_EXTRACTOR = new Machine(GREGTECH,"advanced_fluid_extractor", Medium);

    public static final Machine BASIC_ALLOY_SMELTER = new Machine(GREGTECH,"basic_alloy_smelter", Low);
    public static final Machine BASIC_ARC_FURNACE = new Machine(GREGTECH,"basic_arc_furnace", Low);
    public static final Machine BASIC_ASSEMBLING_MACHINE = new Machine(GREGTECH,"basic_assembling_machine", Low);
    public static final Machine BASIC_AUTO_WORKBENCH = new Machine(GREGTECH,"basic_auto_workbench", Low);
    public static final Machine BASIC_BENDING_MACHINE = new Machine(GREGTECH,"basic_bending_machine", Low);
    public static final Machine BASIC_CENTRIFUGE = new Machine(GREGTECH,"basic_centrifuge", Low);
    public static final Machine BASIC_CIRCUIT_ASSEMBLER = new Machine(GREGTECH,"basic_circuit_assembler", Low);
    public static final Machine BASIC_COMPRESSOR = new Machine(GREGTECH,"basic_compressor", Low);
    public static final Machine BASIC_ELECTRIC_FURNACE = new Machine(GREGTECH,"basic_electric_furnace", Low);
    public static final Machine BASIC_FLUID_EXTRACTOR = new Machine(GREGTECH,"basic_fluid_extractor", Low);
    public static final Machine BASIC_FORGE_HAMMER = new Machine(GREGTECH,"basic_forge_hammer", Low);
    public static final Machine BASIC_LATHE = new Machine(GREGTECH,"basic_lathe", Low);
    public static final Machine BASIC_MACERATOR = new Machine(GREGTECH,"basic_macerator", Low);
    public static final Machine BASIC_MIXER = new Machine(GREGTECH,"basic_mixer", Low);
    public static final Machine BASIC_ROCK_BREAKER = new Machine(GREGTECH,"basic_rock_breaker", Low);
    public static final Machine BASIC_SIFTER = new Machine(GREGTECH,"basic_sifter", Low);
    public static final Machine BASIC_WIREMILL = new Machine(GREGTECH,"basic_wiremill", Low);
    public static final Machine CROP_MANAGER_LV = new Machine(GREGTECH,"crop_manager_lv", Low);
    public static final Machine ELECTRIC_BLAST_FURNACE_LV_NICU = new Machine(GREGTECH,"electric_blast_furnace_lv_nicu", Low, 400);
    public static final Machine LOW_VOLTAGE_FLUID_TANK = new Machine(GREGTECH,"low_voltage_fluid_tank",None);
    public static final Machine REINFORCED_LAVA_BOILER = new Machine(GREGTECH,"reinforced_lava_boiler");
}