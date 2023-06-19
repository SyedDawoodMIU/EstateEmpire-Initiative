export interface iAuthState {
  isAuthenticated?: boolean;
  accessToken: string;
  refreshToken: string;
}

export interface iProperty {
  id?: number;
  title: string;
  price: number;
  // property: string;
}
