import { NativeModules } from 'react-native';

interface SslPinning {
  setup(): void;
  addPublicKey(url: string, key: string): void;
  save(): void;
}

const SslPinning: SslPinning = NativeModules.SslPinning;

export default SslPinning;
