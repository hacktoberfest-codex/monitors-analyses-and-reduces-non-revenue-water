export interface Transaction {
    name: string
    series: Series[]
}
export interface Series {
    name: string
    value: string
}

export interface RevenueByLocation {
    name: string
    value: number
}