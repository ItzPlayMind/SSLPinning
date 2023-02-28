#import <Foundation/Foundation.h>
#import "SslPinning.h"
#import <TrustKit/TrustKit.h>
#import <TrustKit/TSKPinningValidator.h>
#import <TrustKit/TSKPinningValidatorCallback.h>

@implementation SslPinning
RCT_EXPORT_MODULE(SslPinning);

static NSMutableDictionary *public_keys;

RCT_EXPORT_METHOD(setup)
{
  public_keys = [NSMutableDictionary dictionary];
}

RCT_EXPORT_METHOD(addPublicKey: (NSString *)hostname key: (NSString *)key)
{
  [public_keys setObject: @{
            kTSKIncludeSubdomains: @YES,
            kTSKEnforcePinning: @YES,
            kTSKDisableDefaultReportUri: @YES,
            kTSKPublicKeyHashes : @[
              key,
              @"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB=",
            ],
  } forKey:hostname];

}

RCT_EXPORT_METHOD(save)
{
    NSMutableDictionary *config = [NSMutableDictionary dictionary];
    NSDictionary *keys = [public_keys copy];
    [config setObject:keys forKey:kTSKPinnedDomains];
    [config setObject:@YES forKey:kTSKSwizzleNetworkDelegates];
    NSDictionary *trustKitConfig = [config copy];
    [TrustKit initSharedInstanceWithConfiguration:trustKitConfig];
    [TrustKit sharedInstance].pinningValidatorCallback = ^(TSKPinningValidatorResult *result, NSString *notedHostname, TKSDomainPinningPolicy *policy) {
        if (result.finalTrustDecision == TSKTrustEvaluationFailedNoMatchingPin) {
          
        }
    };
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeSslPinningSpecJSI>(params);
}
#endif


@end
  
