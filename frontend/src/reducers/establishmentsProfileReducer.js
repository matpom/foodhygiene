import {
  FETCH_ESTABLISHMENTS_PROFILE_FULFILLED,
  FETCH_ESTABLISHMENTS_PROFILE_PENDING,
  FETCH_ESTABLISHMENTS_PROFILE_REJECTED,
} from '../actions/constants';

const initialState = {
  ratingDistribution: [],
  pending: false,
};

export default function establishmentsProfileReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_ESTABLISHMENTS_PROFILE_PENDING:
      return { ...state, pending: true };
    case FETCH_ESTABLISHMENTS_PROFILE_FULFILLED:
      return {
        ...state,
        ratingDistribution: action.payload.data.ratingDistribution,
        pending: false,
      };
    case FETCH_ESTABLISHMENTS_PROFILE_REJECTED:
      return { ...state, pending: false, error: true };
    default:
      return state;
  }
}
