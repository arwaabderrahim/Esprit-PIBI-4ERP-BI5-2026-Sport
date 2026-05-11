import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

export interface HealthResponse {
  status: string;
  time: string;
  models: {
    classification: boolean;
    regression: boolean;
    clustering: boolean;
    timeseries: boolean;
  };
  model_accuracy: number;
}

export interface ClassificationInput {
  Pts_A: number;
  Pos_A: number;
  Move_A: number;
  Level_A: string;
  Pts_B: number;
  Pos_B: number;
  Move_B: number;
  Level_B: string;
  rank_diff: number;
  pts_diff: number;
  Year: number;
  Month: number;
  Quarter: number;
  IsWeekend: number;
  Gender: string;
  Match_type: string;
}

export interface ClassificationResult {
  prediction: number;
  label: string;
  win_probability: number;
  model: string;
}

export interface ClusteringInput {
  Points: number;
  Position: number;
  Move: number;
  total_wins: number;
  total_loss: number;
  total_matchs: number;
  win_rate: number;
  Level: string;
  Genre: string;
}

export interface ClusteringResult {
  cluster: number;
  profil: string;
}

export interface RegressionInput {
  Points__Winner: number;
  Points__Loser: number;
  Points_W1: number;
  Position_W1: number;
  points_diff: number;
  nb_sets: number;
  is_3sets: number;
  Year: number;
  Month: number;
  Quarter: number;
  IsWeekend: number;
  Gender: string;
  Match_type: string;
  Level_W1: string;
}

export interface RegressionResult {
  prediction_log: number;
  prize_money_eur: number;
}

export interface TimeSeriesResult {
  ds: string;
  yhat: number;
  yhat_lower: number;
  yhat_upper: number;
}

export interface RetrainResult {
  status: string;
  returncode: number;
  output: string;
  error: string;
}

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getHealth(): Observable<HealthResponse> {
    return this.http.get<HealthResponse>(`${this.baseUrl}/health`)
      .pipe(catchError(this.handleError));
  }

  predictClassification(data: ClassificationInput): Observable<ClassificationResult> {
    return this.http.post<ClassificationResult>(`${this.baseUrl}/predict/classification`, data)
      .pipe(catchError(this.handleError));
  }

  predictClustering(data: ClusteringInput): Observable<ClusteringResult> {
    return this.http.post<ClusteringResult>(`${this.baseUrl}/predict/clustering`, data)
      .pipe(catchError(this.handleError));
  }

  predictRegression(data: RegressionInput): Observable<RegressionResult> {
    return this.http.post<RegressionResult>(`${this.baseUrl}/predict/regression`, data)
      .pipe(catchError(this.handleError));
  }

  predictTimeSeries(periods: number = 6): Observable<TimeSeriesResult[]> {
    return this.http.post<TimeSeriesResult[]>(`${this.baseUrl}/predict/timeseries`, { periods })
      .pipe(catchError(this.handleError));
  }

  retrain(): Observable<RetrainResult> {
    return this.http.post<RetrainResult>(`${this.baseUrl}/retrain`, {})
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    let msg = 'Erreur inconnue';
    if (error.status === 0) {
      msg = 'Backend inaccessible — vérifiez que le serveur FastAPI tourne sur port 8000';
    } else if (error.status === 503) {
      msg = 'Modèle ML non disponible';
    } else if (error.error?.detail) {
      msg = error.error.detail;
    }
    return throwError(() => new Error(msg));
  }
}
