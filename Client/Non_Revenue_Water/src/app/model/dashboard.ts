import { RevenueByLocation, Transaction } from "./transaction"

export interface Dashboard {
    userName: String
    accountBalance: number
    numberOfComplaints: number
    todaysRevenue: number
    totalUsers: number
    transactions: Transaction[]
    revenueByLocations: RevenueByLocation[]
    waterFlows:Transaction[]

}