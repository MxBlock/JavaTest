public class MBS {
    private void computeNewRange(){
    global RE_START, RE_END, IM_START, IM_END, RE_RANGE, IM_RANGE
    # New middle of screen is mousePosScreen | convertP2G(mousePosition + HalfNewScreen)
    RE_END = RE_START + ((mousePosPixel[0] + (SCREEN_WIDTH*ZOOM_FACTOR)/2)/ SCREEN_WIDTH) * RE_RANGE
    RE_START = RE_START + ((mousePosPixel[0] - (SCREEN_WIDTH*ZOOM_FACTOR)/2)/ SCREEN_WIDTH) * RE_RANGE
    IM_END = IM_START + ((mousePosPixel[1] + (SCREEN_HEIGHT*ZOOM_FACTOR)/2)/ SCREEN_HEIGHT) * IM_RANGE
    IM_START = IM_START + ((mousePosPixel[1] - (SCREEN_HEIGHT*ZOOM_FACTOR)/2)/ SCREEN_HEIGHT) * IM_RANGE
    # Compute new ranges
    RE_RANGE = RE_END - RE_START
    IM_RANGE = IM_END - IM_START
}


    private void computeMBS(){
    t0 = time.time()
    
    for x,y in product(range(0, SCREEN_WIDTH),range(0, SCREEN_HEIGHT)){ 
        # Convert pixel coordinate to complex number
        c = complex((RE_START + (x / SCREEN_WIDTH) * RE_RANGE), (IM_START + (y / SCREEN_HEIGHT) * IM_RANGE))
        color = 0
        z = 0
        col = [0, 0, 0]
        for n in range(MAX_ITER){
            if (abs(z) > 2){
                /* 
                match COLOR_MODE:
                    case 1:
                        color = 255 if n < MAX_ITER else 0
                        col = [color,color,color]
                        break
                    case 2:
                        color = 255 - int(n * 255 / MAX_ITER)
                        col = [color,color,color]
                        break
                    case 3:
                        if n <= 255:
                            col[0] = n
                        else:
                            col[0] = 255
                            if n <= 510:
                                col[1] = 255
                            else:
                                col[1] = 255
                                col[2] = 255
                        break
                */
                // The color depends on the number of iterations
                //color = 255 - int(n * 255 / MAX_ITER)

                // Fast color mode - black/white
                //color = 255 if n < MAX_ITER else 0

                // Full Color
                if n <= 255:
                    col[0] = n
                else:
                    col[0] = 255
                    if n <= 510:
                        col[1] = 255
                    else:
                        col[1] = 255
                        col[2] = 255
                break
    }
            z = z*z + c
    }
        pixels[x,y] = col
    }
        
    t1 = time.time()
    print(str(t1-t0)+ "\t""\t" + str(RE_START) + "\t" + str(RE_END) + "\t" + str(RE_RANGE) + "\t\t" + str(IM_START) + "\t" + str(IM_END) + "\t" + str(IM_RANGE))
}
}
