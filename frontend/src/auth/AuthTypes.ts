export interface iAuthState {
  authenticated?: boolean;
  accessToken: string;
  refreshToken: string;
}

export interface iProperty {
  id?: number;
  title: string;
  price: number;
}
