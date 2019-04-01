package com.sensiblemetrics.api.sqoola.common.aspect;

@Aspect
@Component
public class FeaturesAspect {

    private static final Logger LOG = LogManager.getLogger(FeaturesAspect.class);

    @Around(value = "@within(featureAssociation) || @annotation(featureAssociation)")
    public Object checkAspect(ProceedingJoinPoint joinPoint, FeatureAssociation featureAssociation) throws Throwable {
        if (featureAssociation.value().isActive()) {
            return joinPoint.proceed();
        } else {
            LOG.info("Feature " + featureAssociation.value().name() + " is not enabled!");
            return null;
        }
    }

}
