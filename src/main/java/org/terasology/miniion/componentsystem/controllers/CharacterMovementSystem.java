/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.miniion.componentsystem.controllers;

import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.entitySystem.systems.UpdateSubscriberSystem;
import org.terasology.logic.characters.CharacterComponent;
import org.terasology.logic.characters.CharacterMoveInputEvent;
import org.terasology.miniion.components.NPCMovementInputComponent;
import org.terasology.registry.In;

/**
 * This updates everything but LocalPlayer because LocalPlayerSystem is currently updating LocalPlayer.
 * 
 */
@RegisterSystem(RegisterMode.AUTHORITY)
public class CharacterMovementSystem implements UpdateSubscriberSystem {
    @In
    private EntityManager entityManager;

    private int inputSequenceNumber = 1;

    @Override
    public void initialise() {
    }

    @Override
    public void shutdown() {
    }

    @Override
    public void update(float delta) {
        for (EntityRef entity : entityManager.getEntitiesWith(NPCMovementInputComponent.class)) {
            NPCMovementInputComponent characterMovementComponent = entity.getComponent(NPCMovementInputComponent.class);
            CharacterComponent characterComp = entity.getComponent(CharacterComponent.class);

            entity.send(new CharacterMoveInputEvent(inputSequenceNumber++,
                    0, 0,
                    characterMovementComponent.directionToMove,
                    characterMovementComponent.runningRequested,
                    characterMovementComponent.jumpingRequested, (long) delta));
            if(characterMovementComponent.jumpingRequested) characterMovementComponent.jumpingRequested = false;
        }
    }

    @Override
    public void preBegin() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postBegin() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void preSave() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postSave() {
        // TODO Auto-generated method stub
        
    }

}
