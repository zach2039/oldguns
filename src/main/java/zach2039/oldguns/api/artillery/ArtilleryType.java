package zach2039.oldguns.api.artillery;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum ArtilleryType
{
    BOMBARD(0, "ArtilleryBombard"),
    MORTAR(1, "ArtilleryMortar"),
    HWACHA(2, "ArtilleryHwacha"),
    CANNON(3, "ArtilleryCannon"),                
    FIELD_GUN(4, "ArtilleryFieldGun"),        
    HOWITZER(5, "ArtilleryHowitzer");
    
    private static final Map<Integer, ArtilleryType> BY_ID = Maps.<Integer, ArtilleryType>newHashMap();
    private final int id;
    private final String name;

    private ArtilleryType(int idIn, String nameIn)
    {
        this.id = idIn;
        this.name = nameIn;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    @SideOnly(Side.CLIENT)
    public static ArtilleryType getById(int idIn)
    {
    	ArtilleryType entityartillery$type = BY_ID.get(Integer.valueOf(idIn));
        return entityartillery$type == null ? CANNON : entityartillery$type;
    }

    static
    {
        for (ArtilleryType entityartillery$type : values())
        {
            BY_ID.put(Integer.valueOf(entityartillery$type.getId()), entityartillery$type);
        }
    }
    
    public static ArtilleryType getTypeFromString(String nameIn)
    {
        for (int i = 0; i < values().length; ++i)
        {
            if (values()[i].getName().equals(nameIn))
            {
                return values()[i];
            }
        }

        return values()[0];
    }
}
