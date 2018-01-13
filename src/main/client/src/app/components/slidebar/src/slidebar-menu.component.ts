import {Component, ViewEncapsulation, Input, Output, EventEmitter, OnInit, OnDestroy} from '@angular/core';
import {Router, Routes, NavigationEnd} from '@angular/router';
import {Subscription} from 'rxjs/Rx';

import { CONSTANT } from '../../../utils/constant';
import {GlobalState} from "../../../global.state";
import { RouteService } from '../../../service/route';

declare var jQuery;

@Component({
  selector: 'slidebar-menu',
  templateUrl: './slidebar-menu.html'
})
export class SlidebarMenu implements OnInit, OnDestroy {
  eventCode:string = 'SlidebarMenu';

  isOrgAdmin: boolean;

  @Input()
  menuItems: any;
  currLink: string;

   showHoverElem:boolean;
   hoverElemHeight:number;
   hoverElemTop:number;
   outOfArea:number = -200;

  constructor(private _router:Router, private _state: GlobalState, private _routeService: RouteService) {
    this.currLink = _router.url;

    if (CONSTANT.PROFILE) {
      this.isOrgAdmin = CONSTANT.PROFILE.orgPrivilege.org_admin;
    }
  }

  ngOnInit() {
    this._state.subscribe(CONSTANT.STATE_CHANGE_PROFILE, this.eventCode, (profile) => {
      console.log(CONSTANT.STATE_CHANGE_PROFILE, profile);

      this.isOrgAdmin = CONSTANT.PROFILE.orgPrivilege.org_admin;
    });
    this._state.subscribe(CONSTANT.STATE_CHANGE_ORGS, this.eventCode, (data) => {
      console.log(CONSTANT.STATE_CHANGE_ORGS, data);

      this.isOrgAdmin = CONSTANT.PROFILE.orgPrivilege.org_admin;
    });
  }

  public hoverItem($event):void {
    this.showHoverElem = true;
    this.hoverElemHeight = $event.currentTarget.clientHeight;
    // TODO: get rid of magic 66 constant
    this.hoverElemTop = $event.currentTarget.getBoundingClientRect().top - 66;
  }

  public selectItem($event):void {
    this.currLink = $event.item.link;
    this._routeService.navTo($event.item.link);
  }

  ngOnDestroy(): void {
    this._state.unsubscribe(CONSTANT.STATE_CHANGE_PROFILE, this.eventCode);
    this._state.unsubscribe(CONSTANT.STATE_CHANGE_ORGS, this.eventCode);
  };
}
