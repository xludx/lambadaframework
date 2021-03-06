package org.lambadaframework.aws;

import com.amazonaws.services.cloudformation.model.ValidateTemplateRequest;
import org.junit.Test;
import org.lambadaframework.deployer.Deployment;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;


public class CloudformationTest {


    protected Deployment getMockDeployment() {

        Deployment dep = mock(Deployment.class);
        expect(dep.getProjectName())
                .andReturn("test")
                .anyTimes();

        expect(dep.getStage())
                .andReturn("development")
                .anyTimes();

        expect(dep.getRegion())
                .andReturn("eu-west-1")
                .anyTimes();
        replay(dep);
        return dep;
    }

    @Test
    public void testCloudFormationTemplateValidate() {
        Cloudformation cf = new Cloudformation(getMockDeployment());
        cf.getCloudFormationClient().validateTemplate(new ValidateTemplateRequest().withTemplateBody(cf.getCloudformationTemplate()));
    }
}