import {
  FETCH_LOCAL_AUTHORITIES_FULFILLED,
  FETCH_LOCAL_AUTHORITIES_PENDING,
  FETCH_LOCAL_AUTHORITIES_REJECTED,
  SELECT_LOCAL_AUTHORITY,
} from '../actions/constants';

const initialState = {
  list: [],
  selected: null,
  pending: false,
};

export default function localAuthoritiesReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_LOCAL_AUTHORITIES_PENDING:
      return { ...state, pending: true };
    case FETCH_LOCAL_AUTHORITIES_FULFILLED:
      return { ...state, list: action.payload.data, pending: false };
    case FETCH_LOCAL_AUTHORITIES_REJECTED:
      return { ...state, pending: false, error: true };
    case SELECT_LOCAL_AUTHORITY:
      return { ...state, selected: action.selected };
    default:
      return state;
  }
}
