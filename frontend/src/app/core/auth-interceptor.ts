import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth';
import { environment } from '../../environments/environment';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const isRelativeApiRequest = req.url.startsWith('/api/');
  const isAbsoluteApiRequest = !!environment.apiBaseUrl && req.url.startsWith(`${environment.apiBaseUrl}/api/`);
  const shouldAttachToken = isRelativeApiRequest || isAbsoluteApiRequest;

  if (!shouldAttachToken || req.url.includes('/api/public/')) {
    return next(req);
  }

  const token = authService.getAccessToken();

  if (!token) {
    return next(req);
  }

  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`,
    },
  });

  return next(authReq);
};
