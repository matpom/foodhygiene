import { createStore, applyMiddleware } from 'redux';
import promiseMiddleware from 'redux-promise-middleware';
import thunk from 'redux-thunk';
import logger from 'redux-logger';

import rootReducer from './reducers/rootReducer';

export default function configureStore() {
  return createStore(rootReducer, applyMiddleware(promiseMiddleware(), thunk, logger));
}
