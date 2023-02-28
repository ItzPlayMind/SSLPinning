import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import SslPinning from 'ssl-pinning';

export default function App() {
  React.useEffect(() => {
    SslPinning.setup();
    SslPinning.addPublicKey(
      '*.sozvers.at',
      '0hL23B0w06HKRnC95NU6LWh3/MFFxFcVTmcDCuoE19g='
    );
    SslPinning.save();
  }, []);

  return <View style={styles.container}></View>;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
