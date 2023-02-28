
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNSslPinningSpec.h"

@interface SslPinning : NSObject <NativeSslPinningSpec>
#else
#import <React/RCTBridgeModule.h>

@interface SslPinning : NSObject <RCTBridgeModule>
#endif

@end
