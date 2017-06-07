/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.model.bpmn.builder;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.SubProcess;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;

/**
 * @author Sebastian Menski
 */
public class ProcessBuilder extends AbstractProcessBuilder<ProcessBuilder> {

  public ProcessBuilder(BpmnModelInstance modelInstance, Process process) {
    super(modelInstance, process, ProcessBuilder.class);
  }

  public StartEventBuilder startEvent() {
    return startEvent(null);
  }

  public StartEventBuilder startEvent(String id) {
    StartEvent start = createChild(StartEvent.class, id);
    BpmnShape bpmnShape = createBpmnShape(start);
    setCoordinates(bpmnShape);
    return start.builder();
  }

  public EventSubProcessBuilder eventSubProcess(){
    // Create a subprocess, triggered by an event, and add it to modelInstance
    SubProcess subProcess = createChild(SubProcess.class, null);
    subProcess.setTriggeredByEvent(true);

    // Create Bpmn shape so subprocess will be drawn
    BpmnShape targetBpmnShape = createBpmnShape(subProcess);
    setCoordinates(targetBpmnShape);
    resizeSubProcess(targetBpmnShape);

    // Return the eventSubProcessBuilder
    EventSubProcessBuilder eventSubProcessBuilder = new EventSubProcessBuilder(modelInstance, subProcess);
    return eventSubProcessBuilder;

  }

  @Override
  protected void setCoordinates(BpmnShape targetBpmnShape){
    Bounds bounds = targetBpmnShape.getBounds();
    bounds.setX(100);
    bounds.setY(100);
  }
}
