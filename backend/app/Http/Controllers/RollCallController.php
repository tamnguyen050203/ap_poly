<?php

namespace App\Http\Controllers;

use App\Models\RollCall;
use App\Http\Requests\StoreRollCallRequest;
use App\Http\Requests\UpdateRollCallRequest;

class RollCallController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \App\Http\Requests\StoreRollCallRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreRollCallRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Http\Response
     */
    public function show(RollCall $rollCall)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Http\Response
     */
    public function edit(RollCall $rollCall)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateRollCallRequest  $request
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateRollCallRequest $request, RollCall $rollCall)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Http\Response
     */
    public function destroy(RollCall $rollCall)
    {
        //
    }
}
