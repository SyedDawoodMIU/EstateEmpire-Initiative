export const storeToken = (accessToken: string) => {
  
  localStorage.setItem("accessToken", accessToken);
};

export const clearToken = () => {
  localStorage.removeItem("accessToken");
};

export const getToken = () => {
  return localStorage.getItem("accessToken")?.toString();
};

export const storeRefreshToken = (refreshToken: string) => {
  localStorage.setItem("refreshToken", refreshToken);
};

export const clearRefreshToken = () => {
  localStorage.removeItem("refreshToken");
};

export const getRefreshToken = () => {
  return localStorage.getItem("refreshToken")?.toString();
};

export const decodeToken = (token: string) => {
  const payloadBase64 = token.split(".")[1];
  const decodedPayload = atob(payloadBase64);
  const decodedObject = JSON.parse(decodedPayload);
  return decodedObject;
};
