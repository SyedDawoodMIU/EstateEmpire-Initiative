export interface iProperty {
  propertyId?: number
  type?: string
  viewCount?: number
  address?: Address
  propertyDetails?: PropertyDetails
  propertyImage?: PropertyImage[]
}

export interface Address {
  street?: string
  city?: string
  state?: string
  zipCode?: string
  country?: string
}

export interface PropertyDetails {
  id?: number
  bedrooms?: number
  bathrooms?: number
  lotSize?: number
  rentAmount?: number
  securityDepositAmount?: number
  yearBuilt?: number
  description?: string
}

export interface PropertyImage {
  id?: string,
  downloadURL?: string
}
