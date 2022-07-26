package com.example.fruitmat.FeatureCollectors.Domain.UseCaseAddCollector

interface UseCaseAddCollector
/**
 * triggers a dialog window to fill collectors name
 * it has to adjust the recyclerview adapter
 */
{
    fun popDialogView()
    fun updateRecAdapter()
    fun trigger()
}