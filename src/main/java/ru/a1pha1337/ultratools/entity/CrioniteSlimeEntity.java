package ru.a1pha1337.ultratools.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

//TODO: Fix this fcking slime
public class CrioniteSlimeEntity extends SlimeEntity {

    public CrioniteSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);

        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1000d);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(1f);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(20f);
    }

//    @Override
//    protected float getDamageAmount() {
//        return super.getDamageAmount() * 2;
//    }
//
//    @Override
//    protected int getTicksUntilNextJump() {
//        return super.getTicksUntilNextJump() - 20;
//    }

    @Override
    protected void damage(LivingEntity target) {
        if (this.isAlive()) {
            if (this.canSee(target) && target.damage(DamageSource.mob(this), this.getDamageAmount())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.applyDamageEffects(this, target);
            }
        }
    }

//    @Override
//    protected void jump() {
//        Vec3d vec3d = this.getVelocity();
//        this.setVelocity(vec3d.x * 5f, this.getJumpVelocity(), vec3d.z * 5f);
//        this.velocityDirty = true;
//    }

    @Override
    protected boolean canAttack() {
        return true;
    }

//    @Override
//    protected float getVelocityMultiplier() {
//        return super.getVelocityMultiplier();
//    }
}
