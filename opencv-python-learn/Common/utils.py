import numpy as np
import cv2, time

def print_matInfo(name, image):         # 행렬 정보 출력 함수
    match image.dtype:
        case 'uint8':
            mat_type = 'CV_8U'
        case 'int8':
            mat_type = 'CV_8S'
        case 'uint16':
            mat_type = 'CV_16U'
        case 'int16':
            mat_type = 'CV_16S'
        case 'float32':
            mat_type = 'CV_32F'
        case 'float64':
            mat_type = 'CV_64F'
    nchannel = 3 if image.ndim == 3 else 1

    ## depth, channel 출력
    print("%12s: depth(%s), channel(%s) -> mat_type(%sC%d)"
          % (name, image.dtype, nchannel, mat_type, nchannel))
