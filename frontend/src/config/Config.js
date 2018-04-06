import env from './env.json';

const nodeEnv = process.env.NODE_ENV || 'development';
const configuration = env[nodeEnv];

export default function config() {
  return configuration;
}
