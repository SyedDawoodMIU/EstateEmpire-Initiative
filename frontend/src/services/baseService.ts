import axios, { AxiosInstance, AxiosRequestConfig } from "axios";
import {
  storeToken,
  clearToken,
  getToken,
  decodeToken,
} from "../utils/tokenUtils";
import { getAccessTokenWithRefreshToken } from "../auth/AuthAction";

const baseURL = "http://localhost:9990/api/v1";

export const apiClient = axios.create({
  baseURL: baseURL,
  headers: {
    "Content-type": "application/json",
  },
});

export const apiClientSecure = axios.create({
  baseURL: baseURL,
  headers: {
    "Content-type": "application/json",
    Authorization: "Bearer " + getToken(),
  },
});

const logError = (error: any) => {
  console.error("Request failed:", error);
};

const handleResponse = (response: any) => {
  if (response.status >= 200 && response.status < 300) {
    return response.data;
  } else {
    throw new Error(`Request failed with status ${response.status}`);
  }
};

const makeRequest = async (
  client: AxiosInstance,
  method: string,
  url: string,
  config: AxiosRequestConfig
) => {
  try {
    const response = await client.request({
      method,
      url,
      ...config,
    });
    return handleResponse(response);
  } catch (error: any) {
    if (error.response && error.response.status === 401) {
      // Token expired, attempt token refresh
      try {
        const newAccessToken = getAccessTokenWithRefreshToken();
        client.defaults.headers["Authorization"] = "Bearer " + newAccessToken;

        // Retry the original request with the new access token
        const response = await client.request({
          method,
          url,
          ...config,
        });
        return handleResponse(response);
      } catch (error) {
        logError(error);
      }
    } else {
      logError(error);
    }
  }
};

export const get = async (
  url: string,
  params: any,
  useSecureClient = false
) => {
  const client = useSecureClient ? apiClientSecure : apiClient;
  return makeRequest(client, "get", url, { params });
};

export const post = async (url: string, data: any, useSecureClient = false) => {
  const client = useSecureClient ? apiClientSecure : apiClient;
  return makeRequest(client, "post", url, { data });
};

export const put = async (url: string, data: any, useSecureClient = false) => {
  const client = useSecureClient ? apiClientSecure : apiClient;
  return makeRequest(client, "put", url, { data });
};

export const patch = async (
  url: string,
  data: any,
  useSecureClient = false
) => {
  const client = useSecureClient ? apiClientSecure : apiClient;
  return makeRequest(client, "patch", url, { data });
};

export const del = async (url: string, useSecureClient = false) => {
  const client = useSecureClient ? apiClientSecure : apiClient;
  return makeRequest(client, "delete", url, {});
};

