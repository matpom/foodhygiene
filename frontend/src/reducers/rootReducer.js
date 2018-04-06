import { combineReducers } from 'redux';
import localAuthoritiesReducer from './localAuthoritiesReducer';
import establishmentsProfileReducer from './establishmentsProfileReducer';
import notificationBarReducer from './notificationBarReducer';

const rootReducer = combineReducers({
  localAuthorities: localAuthoritiesReducer,
  establishmentsProfile: establishmentsProfileReducer,
  notificationBar: notificationBarReducer,
});

export default rootReducer;
