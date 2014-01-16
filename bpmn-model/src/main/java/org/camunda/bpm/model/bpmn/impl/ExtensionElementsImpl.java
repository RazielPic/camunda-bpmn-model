/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.model.bpmn.impl;

import org.camunda.bpm.model.bpmn.ExtensionElements;
import org.camunda.bpm.xml.model.ModelBuilder;
import org.camunda.bpm.xml.model.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.xml.model.type.ModelElementType;
import org.camunda.bpm.xml.model.type.ModelElementTypeBuilder;

import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.BPMN20_NS;
import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.BPMN_ELEMENT_EXTENSION_ELEMENTS;

/**
 * @author Daniel Meyer
 *
 */
public class ExtensionElementsImpl extends AbstractBpmnModelElement implements ExtensionElements {

  public static ModelElementType TYPE;

  public static void registerType(ModelBuilder modelBuilder) {

    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(ExtensionElements.class, BPMN_ELEMENT_EXTENSION_ELEMENTS)
      .namespaceUri(BPMN20_NS)
      .instanceProvider(new ModelElementTypeBuilder.ModelTypeInstanceProvider<ExtensionElements>() {
        public ExtensionElements newInstance(ModelTypeInstanceContext instanceContext) {
          return new ExtensionElementsImpl(instanceContext);
        }
      });

    TYPE = typeBuilder.build();
  }

  public ExtensionElementsImpl(ModelTypeInstanceContext context) {
    super(context);
  }

}