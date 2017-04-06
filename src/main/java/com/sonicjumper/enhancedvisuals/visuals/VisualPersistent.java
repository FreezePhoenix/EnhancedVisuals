package com.sonicjumper.enhancedvisuals.visuals;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.sonicjumper.enhancedvisuals.visuals.types.VisualType;

import net.minecraft.entity.player.EntityPlayer;

public class VisualPersistent extends Visual {
	
	public ArrayList<Visual> subVisuals = new ArrayList<>();
	
	public VisualPersistent(VisualType type, float intensity) {
		super(type, intensity);
	}

	@Override
	public boolean hasFinished() {
		return false;
	}
	
	@Override
	public void onTick(@Nullable EntityPlayer player)
	{
		int i = 0;
		while(i < subVisuals.size())
		{
			if(subVisuals.get(i).hasFinished())
				subVisuals.remove(i);
			else{
				subVisuals.get(i).onTick(player);
				i++;
			}
		}
	}
	
	public void addVisual(Visual visual)
	{
		subVisuals.add(visual);
	}
	
	@Override
	public float getIntensity()
	{
		float intensity = this.intensity;
		for (int i = 0; i < subVisuals.size(); i++) {
			intensity += subVisuals.get(i).getIntensity();
		}
		return intensity;
	}

	@Override
	public boolean isRandomized() {
		return false;
	}
	
}
