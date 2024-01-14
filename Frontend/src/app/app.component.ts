import { Component } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  showHeader: boolean = true;
  showSidebar: boolean = true;

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.showHeader = this.shouldShowHeader(this.activatedRoute);
        this.showSidebar = this.shouldShowSidebar(this.activatedRoute);
      }
    });
  }

  private shouldShowHeader(route: ActivatedRoute): boolean {
    return !route.snapshot.children.some(child =>
      child.routeConfig?.path === 'login' || child.routeConfig?.path === 'registration'
    );
  }

  private shouldShowSidebar(route: ActivatedRoute): boolean {
    return !route.snapshot.children.some(child =>
      child.routeConfig?.path === 'login' || child.routeConfig?.path === 'registration'
    );
  }
}
