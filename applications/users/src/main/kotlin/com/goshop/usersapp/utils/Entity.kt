package com.goshop.usersapp.utils

abstract class Entity<T, TId> {
  abstract val id: TId
//  abstract fun sameIdentityAs(other: T): Boolean
//
//  @Suppress("UNCHECKED_CAST")
//  override fun equals(other: Any?): Boolean {
//    if (this === other) return true
//    if (other == null || javaClass != other.javaClass) return false
//
//    return sameIdentityAs(other as T)
//  }
//
//  override fun hashCode(): Int {
//    return id?.hashCode() ?: 0
//  }
}
