public class Plan1571769378122 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
for (int i = 0; i < 5 ; i++) {
IncreaseTraffic("A");
}

} else {
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
IncreaseTraffic("A");
}

IncreaseTraffic("A");

} else {
for (int i = 0; i < 4 ; i++) {
DecreaseDimmer("C");
}

}

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}
}
