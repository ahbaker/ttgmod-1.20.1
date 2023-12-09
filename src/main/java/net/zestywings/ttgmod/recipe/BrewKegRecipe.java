package net.zestywings.ttgmod.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.zestywings.ttgmod.TTGMod;


public class BrewKegRecipe implements Recipe<SimpleInventory> {

    private final ItemStack output;
    private final DefaultedList<Ingredient> ingredients;
    private final ItemStack liquid;
    private final ItemStack container;
    private final int cookTicks;
    private final int makes;
    private final Identifier id;



    public BrewKegRecipe(ItemStack output, DefaultedList<Ingredient> ingredients,
                         ItemStack liquid, ItemStack container, int cookTicks, int makes, Identifier id){
        this.output = output;
        this.ingredients = ingredients;
        this.liquid = liquid;
        this.container = container;
        this.cookTicks = cookTicks;
        this.id = id;
        this.makes = makes;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
}

    @Override
    public boolean fits(int width, int height) {
        return true;
    }



    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()){return false;}
        boolean hasMatch = false;

        if(liquid.getItem() == inventory.getStack(4).getItem()){

            if (ingredients.get(0).test(inventory.getStack(0))) {
                if (ingredients.get(1).test(inventory.getStack(1))) {
                    if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(3).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(3).test(inventory.getStack(3))) {
                        if (ingredients.get(2).test(inventory.getStack(2))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(2).test(inventory.getStack(1))) {
                    if (ingredients.get(1).test(inventory.getStack(2))) {
                        if (ingredients.get(3).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(3).test(inventory.getStack(2))) {
                        if (ingredients.get(1).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(3).test(inventory.getStack(1))) {
                    if (ingredients.get(1).test(inventory.getStack(2))) {
                        if (ingredients.get(2).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(1).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                }
            } else if (ingredients.get(1).test(inventory.getStack(0))) {
                if (ingredients.get(0).test(inventory.getStack(1))) {
                    if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(3).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(3).test(inventory.getStack(2))) {
                        if (ingredients.get(2).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(2).test(inventory.getStack(1))) {
                    if (ingredients.get(0).test(inventory.getStack(2))) {
                        if (ingredients.get(3).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(3).test(inventory.getStack(2))) {
                        if (ingredients.get(0).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(3).test(inventory.getStack(1))) {
                    if (ingredients.get(0).test(inventory.getStack(2))) {
                        if (ingredients.get(2).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(0).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                }
            } else if (ingredients.get(2).test(inventory.getStack(0))) {
                if (ingredients.get(0).test(inventory.getStack(1))) {
                    if (ingredients.get(1).test(inventory.getStack(2))) {
                        if (ingredients.get(3).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(3).test(inventory.getStack(2))) {
                        if (ingredients.get(1).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(1).test(inventory.getStack(1))) {
                    if (ingredients.get(0).test(inventory.getStack(2))) {
                        if (ingredients.get(3).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(3).test(inventory.getStack(2))) {
                        if (ingredients.get(0).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(3).test(inventory.getStack(1))) {
                    if (ingredients.get(0).test(inventory.getStack(2))) {
                        if (ingredients.get(1).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(1).test(inventory.getStack(2))) {
                        if (ingredients.get(0).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                }
            } else if (ingredients.get(3).test(inventory.getStack(0))) {
                if (ingredients.get(0).test(inventory.getStack(1))) {
                    if (ingredients.get(1).test(inventory.getStack(2))) {
                        if (ingredients.get(2).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(1).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(1).test(inventory.getStack(1))) {
                    if (ingredients.get(0).test(inventory.getStack(2))) {
                        if (ingredients.get(2).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(0).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                } else if (ingredients.get(2).test(inventory.getStack(1))) {
                    if (ingredients.get(0).test(inventory.getStack(2))) {
                        if (ingredients.get(1).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    } else if (ingredients.get(2).test(inventory.getStack(2))) {
                        if (ingredients.get(0).test(inventory.getStack(3))) {
                            hasMatch = true;
                        }
                    }
                }
            }
        }

        return hasMatch;

    }





    @Override
    public DefaultedList<ItemStack> getRemainder(SimpleInventory inventory) {
        return Recipe.super.getRemainder(inventory);
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }


    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    public ItemStack getOutput(){
        return output.copy();
    }

    public ItemStack getResult() { return output.copy();}

    public ItemStack getLiquid(){
        return liquid;
    }

    public ItemStack getContainer(){
        return container;
    }

    public int getAmount(){
        return makes;
    }

    public int getTime(){
        return cookTicks;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override


    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }


    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BrewKegRecipe> {
        Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "brew_keg_recipe";         // this is the name given in the json file
    }



    public static class Serializer implements RecipeSerializer<BrewKegRecipe> {
        Serializer() { }
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "brew_keg_recipe";        // this is the name given in the json file


        @Override
        public BrewKegRecipe read(Identifier id, JsonObject json) {

            ItemStack output = new ItemStack(JsonHelper.getItem(json, "output"),1);

            TTGMod.LOGGER.info(JsonHelper.getString(json, "type" ));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(4, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack liquid = new ItemStack(JsonHelper.getItem(json, "liquid"),1);

            ItemStack container = new ItemStack(JsonHelper.getItem(json, "container"),1);

            int cookTicks = JsonHelper.getInt(json,"cookTicks");
            int makes = JsonHelper.getInt(json,"makes");

            return new BrewKegRecipe(output, inputs, liquid, container, cookTicks, makes, id);
        }

        @Override
        public BrewKegRecipe read(Identifier id, PacketByteBuf buf) {
            ItemStack output = buf.readItemStack();
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(4, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }
            ItemStack liquid = buf.readItemStack();
            ItemStack container = buf.readItemStack();
            int cookTicks = buf.readInt();
            int makes = buf.readInt();
            return new BrewKegRecipe(output, inputs, liquid, container, cookTicks, makes, id);
        }

        @Override
        public void write(PacketByteBuf buf, BrewKegRecipe recipe) {
            buf.writeItemStack(recipe.getOutput());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getLiquid());
            buf.writeItemStack(recipe.getContainer());
            buf.writeInt(recipe.cookTicks);
            buf.writeInt(recipe.makes);
        }
    }






}
