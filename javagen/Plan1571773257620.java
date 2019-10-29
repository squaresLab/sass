public class Plan1571773257620 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
DecreaseTraffic("B");
} else {
DecreaseDimmer("B");
}

for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

} else {
IncreaseTraffic("B");
}

}



}
}
