package net.blaster.nevermod.util;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.blaster.nevermod.NeverMod;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class NeverItemProp {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE= DeferredRegister.createDataComponents(NeverMod.MOD_ID);

    public record GemRecord(int gem_id) {
    }
    public static final Codec<GemRecord> GemCodec = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("gem_id").forGetter(GemRecord::gem_id)
            ).apply(instance, GemRecord::new)
    );


    public static final DeferredHolder<DataComponentType<?>,DataComponentType<Integer>> GemId = register("gem_id", (builder) ->  (builder.persistent(GemCodec.INT)) );

    //public static final DeferredHolder<DataComponentType<?>,DataComponentType<Mob>> Mobheld=register("mobheld", (mobBuilder) ->  (mobBuilder.persistent(myMobCodec2)) );

    private static <T>DeferredHolder<DataComponentType<?>,DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator)
    {return DATA_COMPONENT_TYPE.register(name,()->builderUnaryOperator.apply(DataComponentType.builder()).build()); }

    public static void register(IEventBus eventBus){
        DATA_COMPONENT_TYPE.register(eventBus);
    }


    public static void addCustomItemProp(){
        //ItemProperties.register(ModItems.LinkingGem.get(), new ResourceLocation(NeverMod.MOD_ID,"mobHeld"),(stack)->stack.get());
    }
}

//
//    public record GemRecord(Mob MobHeld) {
//        Mob getM(){return MobHeld;}
//        void setM(Mob m){MobHeld=m;}
//    }
//static Mob mobdef=null;
//static GemRecord gr=new GemRecord(mobdef);

//    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, NeverMod.MOD_ID);
//
//    private static final DeferredHolder<AttachmentType<?>,AttachmentType<Mob>> MobAttachment = ATTACHMENT_TYPES.register(
//            "mobheld", () -> AttachmentType.builder(()->0).serialize(Codec.Mob).build()
//    );

//private static final StreamCodec<ByteBuf,GemRecord> GemCodec= StreamCodec.unit(new GemRecord(mobdef));

//    public static final Codec<GemRecord> GemCodec = RecordCodecBuilder.create(instance ->
//            instance.group(
//                    Codec.unit(mobdef).fieldOf("mobheld").forGetter(GemRecord::MobHeld)
//            ).apply(instance, GemRecord::new)
//    );



//public static final Codec<Mob> MobCodec2 = Codec.lazyInitialized(()->Codec.unit(mobdef));

//    public static final Codec<Mob> myMobCodec = new Codec<Mob>() {
//        GemRecord gemRecord=new GemRecord(null);
//        Mob mob=null;
//        @Override
//        public <T> DataResult<T> encode(Mob input, DynamicOps<T> ops, T prefix) {
//
//            return ops.set(ops.getMap(),input.getEncodeId(),input);
//        }
//
//        @Override
//        public <T> DataResult<Pair<Mob, T>> decode(DynamicOps<T> ops, T input) {
//            return ops.get(input,);
//        }
//    };
//    public static final Codec<Mob> myMobCodec = new Codec<Mob>() {
//
//    @Override
//    public <T> DataResult<T> encode(Mob input, DynamicOps<T> ops, T prefix) {
//        return DataResult.success(ops.set(prefix,input.getEncodeId(),prefix));
//    }
//
//    @Override
//    public <T> DataResult<Pair<Mob, T>> decode(DynamicOps<T> ops, T input) {
//        return  DataResult.success(new Pair<>((Mob) input, input));
//    }
//
//    };
//
//    public static final Codec<Mob> myMobCodec2 = new Codec<Mob>() {
//        Mob mob;
//        @Override
//        public <T> DataResult<T> encode(Mob input, DynamicOps<T> ops, T prefix) {
//            mob=input;
//            System.out.println("enc");
//            return (DataResult<T>) DataResult.success(mob);
//        }
//
//        @Override
//        public <T> DataResult<Pair<Mob, T>> decode(DynamicOps<T> ops, T input) {
//            return DataResult.success(new Pair<>(mob,input));
//        }
//    };
